
resource "aws_ecr_repository" "hello" {
  name         = "${var.project_name}-hello"
  force_delete = true
}

resource "aws_ecr_repository" "world" {
  name         = "${var.project_name}-world"
  force_delete = true
}
