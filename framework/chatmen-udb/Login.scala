case class Login(
  id:       Option[String],
  password: Option[String],
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)
