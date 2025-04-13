import * as cdk from 'aws-cdk-lib';
import {Template} from 'aws-cdk-lib/assertions';
import * as Infra from '../lib/app-stack';

test('ECR Repository Created', () => {
    const app = new cdk.App();
    // WHEN
    const stack = new Infra.AppStack(app, 'AppTestStack', {
        appName: 'quarkus',
    });

    // THEN
    const template = Template.fromStack(stack);

    template.hasResourceProperties('AWS::ECR::Repository', {
        RepositoryName: 'quarkus-app',
        EmptyOnDelete: true,
    });
});
