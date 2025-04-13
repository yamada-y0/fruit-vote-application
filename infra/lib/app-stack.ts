import * as cdk from 'aws-cdk-lib';
import {StackProps} from 'aws-cdk-lib';
import * as ecr from "aws-cdk-lib/aws-ecr";
import {Construct} from 'constructs';
import * as ec2 from "aws-cdk-lib/aws-ec2";
import * as ecs from "aws-cdk-lib/aws-ecs";
import * as ecs_patterns from "aws-cdk-lib/aws-ecs-patterns";

export interface AppStackProps extends StackProps {
    appName: string;
    vpc: ec2.IVpc;
}

export class AppStack extends cdk.Stack {
    constructor(scope: Construct, id: string, props: AppStackProps) {
        super(scope, id, props);
        const repository = new ecr.Repository(this, 'EcrRepository', {
            repositoryName: `${props.appName}-app`,
            removalPolicy: cdk.RemovalPolicy.DESTROY,
            emptyOnDelete: true,
        });

        const cluster = new ecs.Cluster(this, 'EcsCluster', {
            vpc: props.vpc,
            clusterName: `${props.appName}-cluster`,
        });
        new ecs_patterns.ApplicationLoadBalancedFargateService(this, 'FargateService', {
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
        })
    }
}
