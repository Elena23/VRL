/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.vrl.security;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.openpgp.PGPException;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class SecurityUtil {

    private SecurityUtil() {
        throw new AssertionError();
    }

    public static void createPGPKeyPair(
            String identity, String password,
            File pubKeyFile, File privKeyFile, boolean ascii) throws IOException {
        RSAKeyPairGenerator.createKeyPair(
                identity, password, ascii, pubKeyFile, privKeyFile);
    }

    public static void signFilePGP(File privKeyFile, String password,
            File file, File signatureFile, boolean ascii) throws IOException, PGPException {
        DetachedSignatureProcessor.signFile(privKeyFile,
                password, file,
                signatureFile, ascii);
    }

    public static boolean verifyFilePGP(
            File pubKeyFile, String password, File file, File signatureFile)
            throws IOException, PGPException {

        return DetachedSignatureProcessor.verifyFile(pubKeyFile, file, signatureFile);
    }
}
