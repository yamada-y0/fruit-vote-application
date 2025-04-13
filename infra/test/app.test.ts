import * as cdk from "aws-cdk-lib";
import { Template } from "aws-cdk-lib/assertions";
import * as App from "../lib/app-stack";
import * as Network from "../lib/network-stack";

test("Application Resources Created", () => {
    const app = new cdk.App();
    // WHEN
    const networkStack = new Network.NetworkStack(app, "NetworkTestStack");
    const appStack = new App.AppStack(app, "AppTestStack", {
        appName: "quarkus",
        vpc: networkStack.vpc,
    });

    // THEN
    const template = Template.fromStack(appStack);

    template.hasResourceProperties("AWS::ECR::Repository", {
        RepositoryName: "quarkus-app",
        EmptyOnDelete: true,
    });
    template.hasResourceProperties("AWS::ECS::Cluster", {
        ClusterName: "quarkus-cluster",
    });
    template.hasResourceProperties("AWS::ECS::Service", {
        DesiredCount: 1,
    });
    template.hasResourceProperties("AWS::ECS::TaskDefinition", {
        ContainerDefinitions: [
            {
                PortMappings: [
                    {
                        ContainerPort: 8080,
                    },
                ],
            },
        ],
    });
});
