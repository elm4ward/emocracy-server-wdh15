package emocracy

class Channel {

  String title

  static hasMany = [votes: Vote]

  static constraints = {

  }


}
