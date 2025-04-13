import * as cdk from "aws-cdk-lib";
import { Template } from "aws-cdk-lib/assertions";
import * as Infra from "../lib/security-stack";

test("IAM Role Created", () => {
    const app = new cdk.App();
    // WHEN
    const stack = new Infra.SecurityStack(app, "SecurityTestStack");

    // THEN
    const template = Template.fromStack(stack);

    template.hasResourceProperties("AWS::IAM::Role", {
        RoleName: "GitHubActionsRole",
        MaxSessionDuration: 7200,
    });
});
