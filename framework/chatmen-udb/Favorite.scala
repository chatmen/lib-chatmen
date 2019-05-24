case class Favorite(
  id: Option[String],                                  // UserID
  tweetId:   Option[String],                           // tweetID
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)
