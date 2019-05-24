case class Tweet(
  id:                 Option[String]                            // TweetID
  userId:             Option[String],                           // UseriD
  text:               Option[String],                           // Text本文
  isLimitTweetLength: Boolean                                   // 文字数制限
  updatedAt:          LocalDateTime         = NOW,              // データ更新日
  createdAt:          LocalDateTime         = NOW               // データ作成日
)
