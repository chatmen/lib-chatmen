case class Customer(
  id:          Option[String],
  name:        Option[String],
  gender:      Option[String],
  phoneNumber: Option[Int],
  email:       Option[String],
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)
