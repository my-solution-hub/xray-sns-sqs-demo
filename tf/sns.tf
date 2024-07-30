resource "aws_sns_topic" "user_updates" {
  name                        = "${var.project_name}-demo"
  fifo_topic                  = false
  // content_based_deduplication = true
  tracing_config = "Active"
  sqs_success_feedback_sample_rate = 100

  // can be created from web console manually
  sqs_failure_feedback_role_arn = "arn:aws:iam::613477150601:role/SNSFailureFeedback"
  sqs_success_feedback_role_arn = "arn:aws:iam::613477150601:role/SNSSuccessFeedback"
  
}

resource "aws_sns_topic_subscription" "sns_sqs_target" {
  topic_arn = aws_sns_topic.user_updates.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.demo_queue.arn
  endpoint_auto_confirms = true
}
