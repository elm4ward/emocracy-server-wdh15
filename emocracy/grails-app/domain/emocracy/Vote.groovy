package emocracy

import org.joda.time.LocalDateTime

class Vote {

  User user

  LocalDateTime submitted
  Long answer

  static belongsTo = [channel: Channel]

  static constraints = {

  }


  static namedQueries = {

    findRecentByChannelAndUser { cid, uid ->
      channel{
        eq 'id', cid
      }
      user {
        eq 'id', uid
      }
      gt 'submitted', new LocalDateTime().minusMinutes(5)
    }

  }

}
