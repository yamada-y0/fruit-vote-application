import * as cdk from "aws-cdk-lib";
import { StackProps } from "aws-cdk-lib";
import * as ecr from "aws-cdk-lib/aws-ecr";
import { Construct } from "constructs";
import * as ec2 from "aws-cdk-lib/aws-ec2";
import * as ecs from "aws-cdk-lib/aws-ecs";
import * as ecs_patterns from "aws-cdk-lib/aws-ecs-patterns";
import * as dynamodb from "aws-cdk-lib/aws-dynamodb";
import { BillingMode } from "aws-cdk-lib/aws-dynamodb";

export interface AppStackProps extends StackProps {
    appName: string;
    vpc: ec2.IVpc;
}

export class AppStack extends cdk.Stack {
    constructor(scope: Construct, id: string, props: AppStackProps) {
        super(scope, id, props);
        const repository = new ecr.Repository(this, "EcrRepository", {
            repositoryName: `${props.appName}-app`,
            removalPolicy: cdk.RemovalPolicy.DESTROY,
            emptyOnDelete: true,
        });

        const cluster = new ecs.Cluster(this, "EcsCluster", {
            vpc: props.vpc,
            clusterName: `${props.appName}-cluster`,
        });
        const fargateService =
            new ecs_patterns.ApplicationLoadBalancedFargateService(
                this,
                "FargateService",
                {
                    cluster: cluster,
                    cpu: 256,
                    desiredCount: 1,
                    taskImageOptions: {
                        image: ecs.ContainerImage.fromEcrRepository(repository),
                        containerPort: 8080,
                        enableLogging: true,
                    },
                    memoryLimitMiB: 512,
                    publicLoadBalancer: false,
                },
            );

        const voteTable = new dynamodb.Table(this, "VoteTable", {
            partitionKey: {
                name: "userId",
                type: dynamodb.AttributeType.STRING,
            },
            sortKey: {
                name: "fruit",
                type: dynamodb.AttributeType.STRING,
            },
            tableName: "Vote",
            removalPolicy: cdk.RemovalPolicy.DESTROY,
            billingMode: BillingMode.PAY_PER_REQUEST,
        });
        voteTable.grantReadWriteData(
            fargateService.service.taskDefinition.taskRole,
        );

        const fruitCountTable = new dynamodb.Table(this, "FruitCountTable", {
            partitionKey: {
                name: "fruit",
                type: dynamodb.AttributeType.STRING,
            },
            tableName: "FruitCount",
            removalPolicy: cdk.RemovalPolicy.DESTROY,
            billingMode: BillingMode.PAY_PER_REQUEST,
        });
        fruitCountTable.grantReadWriteData(
            fargateService.service.taskDefinition.taskRole,
        );
    }
}
