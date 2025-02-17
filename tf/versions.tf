terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.44.0"
    }
  }
  backend "s3" {
    bucket = "yagr-tfstate-log-us"
    key    = "demo/aws-solutions/xray-sns-sqs-demo"
    region = "us-east-1"
  }
}
