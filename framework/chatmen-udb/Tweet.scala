case class Tweet(
  id:        Option[String],                           // TweetID                                
  userId:    Option[String],                           // UserID
  text:      Option[String],                           // text本文
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)
