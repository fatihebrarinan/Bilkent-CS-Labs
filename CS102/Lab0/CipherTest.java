// Test class for Cipher
public class CipherTest
{

   public static void main(String[] args)
   {
      Cipher cipher = new Cipher();

      // text1 is used for testing, encrypting followed by decrypting should return
      // the original text
      String text1 = "this should be encrypted now!";

      String text1_encrypted = cipher.encrypt(text1);
      String text1_decrypted = cipher.decrypt(text1_encrypted);

      System.out.println("Original Text: " + text1);
      System.out.println("Encrypted Text: " + text1_encrypted);
      System.out.println("Decrypted Text: " + text1_decrypted);
      System.out.println();

      // text2 is the message to be decrypted, something meaningful should print if
      // Cipher is working correctly
      String text2 = "iu, git pldp hehocplvgk vj tiouvgk, plvj jliqay sduh jhgjh. ldeh d kiiy phos!";
      System.out.println(cipher.decrypt(text2));
   }

}
