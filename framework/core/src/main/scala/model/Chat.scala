package chatmen.udb.model

import ixias.model._
import java.time.{LocalDate, LocalDateTime}

case class Chat(
  id:        Option[User.Id],                          // UserID
  message:   Option[String],                           // TweetID
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[Id]
