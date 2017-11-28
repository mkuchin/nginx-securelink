import java.security.MessageDigest
import groovy.transform.Field

@Field final expiry = 300 // 5 min
@Field final secret = ' secret'

String encodeUri(String uri, int userId) {
  int unixTime =  new Date().time/1000
  int expiryTime = unixTime + expiry
  String v = "$expiryTime$uri$userId$secret"
  String md5 = MessageDigest.getInstance("MD5").digest(v.bytes).encodeBase64().toString().tr('+/', '-_').replace('=', '')
  return "${uri}?t=$expiryTime&u=$userId&h=$md5"
}

println encodeUri('/s/', 5)