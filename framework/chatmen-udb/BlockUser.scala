case class BlockUser(
  id: Option[String],
  targetId: Option[String],
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日  
)
