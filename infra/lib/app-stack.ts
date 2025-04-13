import * as cdk from 'aws-cdk-lib';
import {StackProps} from 'aws-cdk-lib';
import * as ecr from "aws-cdk-lib/aws-ecr";
import {Construct} from 'constructs';

export interface AppStackProps extends StackProps {
    appName: string;
}

export class AppStack extends cdk.Stack {
    constructor(scope: Construct, id: string, props: AppStackProps) {
        super(scope, id, props);
        new ecr.Repository(this, 'EcrRepository', {
            repositoryName: `${props.appName}-repository`,
            removalPolicy: cdk.RemovalPolicy.DESTROY,
            emptyOnDelete: true,
        });
    }
}
