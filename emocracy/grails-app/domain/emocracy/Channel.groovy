package emocracy

import org.joda.time.LocalDateTime

class Channel {

  String title

  static hasMany = [votes: Vote]

  static constraints = {

  }

  LocalDateTime getLatestVoteTime(){
    votes?.max { it?.submitted }?.submitted
  }

}
