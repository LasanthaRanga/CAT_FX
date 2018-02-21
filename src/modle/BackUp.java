package modle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class BackUp {

    public static void Backup() {
        String backupPath = getBackupPath();
        Date date = new Date();
        String format = new SimpleDateFormat("dd_MMMMM_yyyy_HH_mm_ss").format(date);
        String name = backupPath + format + ".sql";
        System.out.println(name);

        String dump = getDumpPath();
        String uname = " -uroot";
        String pass = " -p";
        String db = " cat -r";

        Runtime r = Runtime.getRuntime();
        boolean b = true;
        try {
            String s = dump + uname + pass + db + name;
            System.out.println(s);
            r.exec(s);
        } catch (Exception e) {
            e.printStackTrace();
            modle.Allert.notificationError("BackUp", "error");
            b = false;
        }
        if (b) {
            modle.Allert.notificationGood("BackUp", "Done");
        }
    }

    public static String getBackupPath() {
        String parth;
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\cat\\file.lasa"));
            parth = br.readLine();
            br.close();
            return parth;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //br.close();
        }
    }

    public static String getDumpPath() {
        String parth;
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\cat\\dump.lasa"));
            parth = br.readLine();
            br.close();
            return parth;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //br.close();
        }
    }

}
