case class User(
  id:          Option[String],                         // ユーザID 
  name:        Option[String],                         // 名前
  gender:      Option[String],
  phoneNumber: Option[Int],
  email:       Option[String],
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)

object User{
//  val Id = 
}
