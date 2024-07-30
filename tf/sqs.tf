resource "aws_sqs_queue" "demo_queue" {
  name                        = "${var.project_name}-demo"
  fifo_queue                  = false
  // content_based_deduplication = true
}

data "aws_iam_policy_document" "sns_policy" {
  statement {
    sid    = "First"
    effect = "Allow"

    principals {
      type        = "*"
      identifiers = ["*"]
    }

    actions   = ["sqs:SendMessage"]
    resources = [aws_sqs_queue.demo_queue.arn]

    condition {
      test     = "ArnEquals"
      variable = "aws:SourceArn"
      values   = [aws_sns_topic.user_updates.arn]
    }
  }
}

resource "aws_sqs_queue_policy" "sqs_sns_policy" {
  queue_url = aws_sqs_queue.demo_queue.id
  policy    = data.aws_iam_policy_document.sns_policy.json
}