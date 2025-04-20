#!/bin/bash
awslocal dynamodb create-table \
  --table-name Vote \
  --attribute-definitions \
    AttributeName=userId,AttributeType=S \
    AttributeName=fruit,AttributeType=S \
  --key-schema \
    AttributeName=userId,KeyType=HASH \
    AttributeName=fruit,KeyType=RANGE \
  --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
  --region=us-east-1

awslocal dynamodb create-table \
  --table-name FruitCount \
  --attribute-definitions \
    AttributeName=fruit,AttributeType=S \
  --key-schema \
    AttributeName=fruit,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
  --region=us-east-1
echo "#### Dynamodb init completed"
