import java.security.MessageDigest
import groovy.transform.Field

@Field final expiry = 300 // 5 min
@Field final secret = ' secret'

String encodeUri(String uri) {
  int unixTime =  new Date().time/1000
  int expiryTime = unixTime + expiry
  String v = "$expiryTime$uri$secret"
  String md5 = MessageDigest.getInstance("MD5").digest(v.bytes).encodeBase64().toString().tr('+/', '-_').replace('=', '')
  return "${uri}?t=$expiryTime&h=$md5"
}

println encodeUri('/s/')