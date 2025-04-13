import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as ec2 from "aws-cdk-lib/aws-ec2";

export class NetworkStack extends cdk.Stack {
    public readonly vpc: ec2.IVpc;

    constructor(scope: Construct, id: string, props?: cdk.StackProps) {
        super(scope, id, props);
        this.vpc = new ec2.Vpc(this, "VPC", {
            ipAddresses: ec2.IpAddresses.cidr("10.0.0.0/16"),
            subnetConfiguration: [
                {
                    cidrMask: 24,
                    name: "Isolated",
                    subnetType: ec2.SubnetType.PRIVATE_ISOLATED,
                },
            ],
            maxAzs: 2,
        });

        new ec2.InterfaceVpcEndpoint(this, "EcrApiEndpoint", {
            vpc: this.vpc,
            service: ec2.InterfaceVpcEndpointAwsService.ECR,
        });
        new ec2.InterfaceVpcEndpoint(this, "EcrDockerEndpoint", {
            vpc: this.vpc,
            service: ec2.InterfaceVpcEndpointAwsService.ECR_DOCKER,
        });
        new ec2.InterfaceVpcEndpoint(this, "CloudWatchLogsEndpoint", {
            vpc: this.vpc,
            service: ec2.InterfaceVpcEndpointAwsService.CLOUDWATCH_LOGS,
        });
        new ec2.GatewayVpcEndpoint(this, "S3Endpoint", {
            vpc: this.vpc,
            service: ec2.GatewayVpcEndpointAwsService.S3,
        });
        new ec2.GatewayVpcEndpoint(this, "DynamoDbEndpoint", {
            vpc: this.vpc,
            service: ec2.GatewayVpcEndpointAwsService.DYNAMODB,
        });
    }
}
