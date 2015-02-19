package com.saboonchi.tools;
//import com.cybercom.Global;
//import com.cybercom.SignRequest;

import java.security.*;
import java.sql.*;
import java.util.Arrays;

public class DB {
    public static Connection conn = null;
    //public static String dbType = "SQLite";
    public static String dbType = "SQLServer";
    public static Statement connStatement = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=l/gF4a+7Jp;database=test");

        conn = getConnection();

        //System.out.println("test");
        Statement sta = conn.createStatement();
        /*String Sql = "select * from test";
        ResultSet rs = sta.executeQuery(Sql);
        while (rs.next()) {
            System.out.print(rs.getString("id"));
            System.out.print(" - ");
            System.out.print(rs.getString("text"));
            System.out.println("");
        }
        */
        //1408302290003
        //1408301340587

        PreparedStatement insert=null;

        for (long i = 1340587 ; i < 2290003 ; i++ ){
            insert = conn.prepareStatement("INSERT INTO chart1 (time) VALUES (140830"+i+")");
            insert.executeUpdate();
        }

        insert.close();

        conn.close();

    }

    public static void insert1(byte[] priKey)throws SQLException, ClassNotFoundException{
        conn = getConnection();
        Statement sta = conn.createStatement();
        String Sql = "insert into keys (priKey) values (" + priKey+ ")";
        sta.executeUpdate(Sql);
        System.out.print("done!");
        conn.close();
    }

    public static Connection getConnection()throws SQLException, ClassNotFoundException {

        if ((conn == null) || (conn.isClosed())){
            if(dbType.equalsIgnoreCase("SQLServer")){
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection("jdbc:sqlserver://localhost;user=sa;password=l/gF4a+7Jp;database=test");
            }else if(dbType.equalsIgnoreCase("SQLite")){
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            }
        }
        return conn;


    }



    public static int insert(byte[] priKey,byte[] certificate) {
        int id = 0;
        try {

            conn = getConnection();

            //byte[] salt = generateSalt();
            Statement select = conn.createStatement();
            String Sql = "SELECT MAX(id) FROM keys;";
            try{
                ResultSet rs  = select.executeQuery(Sql);
                if (rs.next()){
                    id = ((Number) rs.getObject(1)).intValue();
                }
            }catch (NullPointerException e){

            }
            System.out.println("Max ID : " + id);
            System.out.println("New ID : " + (id+1));
            id+=1;



            PreparedStatement insert = conn.prepareStatement("INSERT INTO keys (id,priKey,pubKey) VALUES ("+id+",?,?)");
            insert.setBytes(1, priKey);
            insert.setBytes(2, certificate);
            insert.executeUpdate();
            insert.close();
            System.out.println("values are succesfully inserted to database");





        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }
    public static int insert(byte[] priKey) {
        int id = 0;
        try {

            conn = getConnection();

            //byte[] salt = generateSalt();
            Statement select = conn.createStatement();
            String Sql = "SELECT MAX(id) FROM keys;";
            try{
                ResultSet rs  = select.executeQuery(Sql);
                if (rs.next()){
                    id = ((Number) rs.getObject(1)).intValue();
                }
            }catch (NullPointerException e){

            }
            System.out.println("Max ID : " + id);
            System.out.println("New ID : " + (id+1));
            id+=1;



            PreparedStatement insert = conn.prepareStatement("INSERT INTO keys (id,priKey) VALUES ("+id+",?)");
            insert.setBytes(1, priKey);
            insert.executeUpdate();
            insert.close();
            System.out.println("values are succesfully inserted to database");





        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }
    public static int insert(byte[] pubKey,int keyID) {
        int id = 0;
        try {

            conn = getConnection();
            if (keyID < 0 ){
                //byte[] salt = generateSalt();
                Statement select = conn.createStatement();
                String Sql = "SELECT MAX(id) FROM keys;";
                try{
                    ResultSet rs  = select.executeQuery(Sql);
                    if (rs.next()){
                        id = ((Number) rs.getObject(1)).intValue();
                    }
                }catch (NullPointerException e){

                }
                System.out.println("Max ID : " + id);
                System.out.println("New ID : " + (id+1));
                keyID = id+1;
            }


            PreparedStatement insert = conn.prepareStatement("INSERT INTO keys (id,pubKey) VALUES ("+keyID+",?)");
            insert.setBytes(1, pubKey);
            insert.executeUpdate();
            insert.close();
            //System.out.println("values are succesfully inserted to database");





        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }
    public static int insert(byte[] wrappedPrivateKey,byte[] pubKey,int keyID,long  keyGenerateStartTimeStamp,long  keyGenerateEndTimeStamp,long keyGenerationDuration ) {
        int id = 0;
        try {

            conn = getConnection();
            if (keyID < 0 ){
                //byte[] salt = generateSalt();
                Statement select = conn.createStatement();
                String Sql = "SELECT MAX(id) FROM keys;";
                try{
                    ResultSet rs  = select.executeQuery(Sql);
                    if (rs.next()){
                        id = ((Number) rs.getObject(1)).intValue();
                    }
                }catch (NullPointerException e){

                }
                System.out.println("Max ID : " + id);
                System.out.println("New ID : " + (id+1));
                keyID = id+1;
            }


            PreparedStatement insert = conn.prepareStatement("INSERT INTO keys (id,priKey,pubKey,keyGenerateStartTimeStamp,keyGenerateEndTimeStamp,keyGenerationDuration) VALUES ("+keyID+",?,?,?,?,?)");
            insert.setBytes(1, wrappedPrivateKey);
            insert.setBytes(2, pubKey);
            insert.setLong(3, keyGenerateStartTimeStamp);
            insert.setLong(4, keyGenerateEndTimeStamp);
            insert.setLong(5, keyGenerationDuration);
            insert.executeUpdate();
            insert.close();
            //System.out.println("values are succesfully inserted to database");





        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }

    public static int insertToBeSignBytes(byte[] toBeSignByte) {
        int id = 0;
        try {

            conn = getConnection();

            //byte[] salt = generateSalt();
            Statement select = conn.createStatement();
            /*
            String Sql = "SELECT MAX(id) FROM test;";
            try{
                ResultSet rs  = select.executeQuery(Sql);
                if (rs.next()){
                    id = ((Number) rs.getObject(1)).intValue();
                }
            }catch (NullPointerException e){

            }
            System.out.println("Max ID : " + id);
            System.out.println("New ID : " + (id+1));
            id+=1;

*/

            PreparedStatement insert = conn.prepareStatement("INSERT INTO signature (toBeSignedBytes) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
            insert.setBytes(1, toBeSignByte);
           // insert.setLong(2, System.currentTimeMillis());
            insert.executeUpdate();
            ResultSet res = insert.getGeneratedKeys();
            res.next();
            id = res.getInt(1);
            insert.close();
            //System.out.println("values are succesfully inserted to database");







        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }
    public static int insertSignatureAndCertificate(int id,byte[] signature,byte[] certificate,long sendTimeStamp,long receivedTimeStamp) {
        try {

            conn = getConnection();

            //byte[] salt = generateSalt();
            Statement select = conn.createStatement();
            /*
            String Sql = "SELECT MAX(id) FROM test;";
            try{
                ResultSet rs  = select.executeQuery(Sql);
                if (rs.next()){
                    id = ((Number) rs.getObject(1)).intValue();
                }
            }catch (NullPointerException e){

            }
            System.out.println("Max ID : " + id);
            System.out.println("New ID : " + (id+1));
            id+=1;

*/

            PreparedStatement insert = conn.prepareStatement("UPDATE signature SET signatureBytes = ? , certificateBytes = ? , requestReceivedTimeStamp = ? , requestFinishedTimeStamp = ?  WHERE requestID = '"+id+"'");
            insert.setBytes(1, signature);
            insert.setBytes(2, certificate);
            insert.setLong(3, sendTimeStamp);
            insert.setLong(4, receivedTimeStamp);
            insert.executeUpdate();
            insert.close();
            System.out.println("values are succesfully inserted to database");







        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }
    

//    public static void signRequestFinalUpdate(SignRequest signRequest) {
//        try {
//            conn = getConnection();
//            PreparedStatement insert = conn.prepareStatement("UPDATE signature SET signatureBytes = ? , certificateBytes = ? , requestStartToProcessTimeStamp = ? ," +
//                    " requestFinishedTimeStamp = ? , status = ? ,processTime = ? , requestGetKeyPairTimeStamp = ? , preGeneratedKeyPair = ? ,requestReceivedTimeStamp = ? ,waitingTime = ?,TestID = ?     WHERE requestID = '"+signRequest.getRequestID()+"'");
//            insert.setBytes(1, signRequest.getSignatureBytes());
//            insert.setBytes(2, signRequest.getCertificateBytes());
//            insert.setLong(3, signRequest.getRequestStartToProcessTimeStamp());
//            insert.setLong(4, signRequest.getRequestFinishedTimeStamp());
//            insert.setInt(5, signRequest.getCurrentStatus());
//            insert.setLong(6, (signRequest.getRequestFinishedTimeStamp()-signRequest.getRequestStartToProcessTimeStamp()));
//            insert.setLong(7, signRequest.getRequestGetKeyPairTimeStamp());
//            insert.setBoolean(8, signRequest.isPreGeneratedKeyPair());
//            insert.setLong(9, signRequest.getRequestReceivedTimeStamp());
//            insert.setLong(10, (signRequest.getRequestStartToProcessTimeStamp() - signRequest.getRequestReceivedTimeStamp()));
//            insert.setString(11, Global.getTestID() ); //,TestID = 'L1010_2'
//
//            insert.executeUpdate();
//
//                insert.close();
//
//                //byte[] salt = generateSalt();
//                //Statement select = conn.createStatement();
//            /*
//            String Sql = "SELECT MAX(id) FROM test;";
//            try{
//                ResultSet rs  = select.executeQuery(Sql);
//                if (rs.next()){
//                    id = ((Number) rs.getObject(1)).intValue();
//                }
//            }catch (NullPointerException e){
//
//            }
//            System.out.println("Max ID : " + id);
//            System.out.println("New ID : " + (id+1));
//            id+=1;
//
//*/
//
//
//                //System.out.println("Final values are successfully updated for requestID ("+signRequest.getRequestID()+") to database");
//
//
//
//
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//
//
//    }

//    public static SignRequest getASignRequest() {
//        int status = 0, requestID = 0;
//        ResultSet rs = null;
//        byte[] toBeSignedBytes=null , signatureBytes = null,certificateBytes=null;
//        SignRequest signRequest = null;
//        long requestReceivedTimeStamp = 0 , requestFinishedTimeStamp = 0, requestStartToProcessTimeStamp=0;
//
//        try {
//            conn = getConnection();
//            connStatement = conn.createStatement();
//
//            if (dbType.equalsIgnoreCase("SQLite")){
//
//                rs = connStatement.executeQuery( "SELECT * FROM signature where status = 0 LIMIT 1;" );
//
//            }else if (dbType.equalsIgnoreCase("SQLServer")){
//
//                String Sql = "SELECT TOP 1 * FROM signature where status = 0";
//                rs  = connStatement.executeQuery(Sql);
//            }
//
//
//
//
//
//
//
//            try {
//                while (rs.next()) {
//                    requestID = rs.getInt("requestID");
//                    toBeSignedBytes = rs.getBytes("toBeSignedBytes");
//                    signatureBytes = rs.getBytes("signatureBytes");
//                    certificateBytes = rs.getBytes("certificateBytes");
//                    status = rs.getInt("status");
//                    requestReceivedTimeStamp = rs.getLong("requestReceivedTimeStamp");
//                    requestFinishedTimeStamp = rs.getLong("requestFinishedTimeStamp");
//                    requestStartToProcessTimeStamp = rs.getLong("requestStartToProcessTimeStamp");
//                }
//
//                if (requestID != 0 && toBeSignedBytes != null && signatureBytes == null && certificateBytes == null && status == 0 && requestFinishedTimeStamp == 0 && requestStartToProcessTimeStamp == 0 ){
//
//                    PreparedStatement update = conn.prepareStatement("UPDATE signature SET status = 1 , requestStartToProcessTimeStamp = "+System.currentTimeMillis()+"  WHERE requestID = '"+requestID+"'");
//                    update.executeUpdate();
//                    update.close();
//                    // System.out.println("values are succesfully inserted to database");
//
//                }
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            } finally {
//                rs.close();
//                connStatement.close();
//            }
//            if (requestID != 0 && toBeSignedBytes != null && signatureBytes == null && certificateBytes == null && status == 0 && requestFinishedTimeStamp == 0 && requestStartToProcessTimeStamp == 0 )
//                signRequest = new SignRequest(requestID,toBeSignedBytes);
//
//
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//
//        return signRequest;
//    } // fixed
//    public static SignRequest[] getSignRequests(int number) {
//        int status = 0, requestID = 0;
//        ResultSet rs=null;
//        byte[] toBeSignedBytes=null , signatureBytes = null,certificateBytes=null;
//        SignRequest[] signRequest = new SignRequest[number];
//        long requestReceivedTimeStamp = 0 , requestFinishedTimeStamp = 0, requestStartToProcessTimeStamp=0;
//
//
//        try {
//            conn = getConnection();
//            connStatement = conn.createStatement();
//
//            if (dbType.equalsIgnoreCase("SQLite")){
//
//                rs = connStatement.executeQuery( "SELECT * FROM signature where status = 0 LIMIT "+number+";" );
//
//            }else if (dbType.equalsIgnoreCase("SQLServer")){
//
//                String Sql = "SELECT TOP "+number+" * FROM signature where status = 0 ";
//                rs  = connStatement.executeQuery(Sql);
//            }
//
//            try {
//                while (rs.next()) {
//                    requestID = rs.getInt("requestID");
//                    toBeSignedBytes = rs.getBytes("toBeSignedBytes");
//                    signatureBytes = rs.getBytes("signatureBytes");
//                    certificateBytes = rs.getBytes("certificateBytes");
//                    status = rs.getInt("status");
//                    requestReceivedTimeStamp = rs.getLong("requestReceivedTimeStamp");
//                    requestFinishedTimeStamp = rs.getLong("requestFinishedTimeStamp");
//                    requestStartToProcessTimeStamp = rs.getLong("requestStartToProcessTimeStamp");
//
//                    if (requestID != 0 && toBeSignedBytes != null && signatureBytes == null && certificateBytes == null && status == 0 && requestFinishedTimeStamp == 0 && requestStartToProcessTimeStamp == 0 ){
//
//                        PreparedStatement update = conn.prepareStatement("UPDATE signature SET status = 1   WHERE requestID = '"+requestID+"'");
//                        update.executeUpdate();
//                        update.close();
//
//                    }
//
//                    number = number-1;
//
//                    if (requestID != 0 && toBeSignedBytes != null && signatureBytes == null && certificateBytes == null && status == 0 && requestFinishedTimeStamp == 0 && requestStartToProcessTimeStamp == 0 )
//                        signRequest[number] = new SignRequest(requestID,toBeSignedBytes);
//
//                    // System.out.println("values are succesfully inserted to database");
//
//                }
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            } finally {
//                rs.close();
//                connStatement.close();
//            }
//
//
//
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//
//        return signRequest;
//    } //fixed

    public static byte[] select(int id) {
        byte[] receivedSalt = null;
        try {

            conn = getConnection();

            //byte[] salt = generateSalt();
            /*
            PreparedStatement insert = conn.prepareStatement("INSERT INTO test (priKey) VALUES (?)");
            insert.setBytes(1, priKey);
            insert.executeUpdate();
            insert.close();
            System.out.println("values are succesfully inserted to database");
*/


            Statement select = conn.createStatement();
            String Sql = "SELECT * FROM keys where id = '"+id+"'";
            ResultSet rs  = select.executeQuery(Sql);

            try {
                while (rs.next()) {
                    receivedSalt = rs.getBytes("pubKey");
                    // if (Arrays.equals(salt, receivedSalt))
                    //    System.out.println("match");
                    //else System.out.println("no match");
                }
            }
            finally {
                rs.close();
                select.close();
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return receivedSalt;
    }
    public static byte[] selectCertificate(int id) {
        byte[] receivedSalt = null;
        try {

            conn = getConnection();

            //byte[] salt = generateSalt();
            /*
            PreparedStatement insert = conn.prepareStatement("INSERT INTO test (priKey) VALUES (?)");
            insert.setBytes(1, priKey);
            insert.executeUpdate();
            insert.close();
            System.out.println("values are succesfully inserted to database");
*/


            Statement select = conn.createStatement();
            String Sql = "SELECT * FROM signature where requestID = '"+id+"'";
            ResultSet rs  = select.executeQuery(Sql);

            try {
                while (rs.next()) {
                    receivedSalt = rs.getBytes("certificateBytes");
                    // if (Arrays.equals(salt, receivedSalt))
                    //    System.out.println("match");
                    //else System.out.println("no match");
                }
            }
            finally {
                rs.close();
                select.close();
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return receivedSalt;
    }
    public static byte[] selectSignature(int id) {
        byte[] receivedSalt = null;
        try {

            conn = getConnection();

            //byte[] salt = generateSalt();
            /*
            PreparedStatement insert = conn.prepareStatement("INSERT INTO test (priKey) VALUES (?)");
            insert.setBytes(1, priKey);
            insert.executeUpdate();
            insert.close();
            System.out.println("values are succesfully inserted to database");
*/


            Statement select = conn.createStatement();
            String Sql = "SELECT * FROM signature where requestID = '"+id+"'";
            ResultSet rs  = select.executeQuery(Sql);

            try {
                while (rs.next()) {
                    receivedSalt = rs.getBytes("signatureBytes");
                    // if (Arrays.equals(salt, receivedSalt))
                    //    System.out.println("match");
                    //else System.out.println("no match");
                }
            }
            finally {
                rs.close();
                select.close();
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return receivedSalt;
    }
    public static byte[] selectToSignBytes(int id) {
        byte[] receivedSalt = null;
        try {

            conn = getConnection();

            //byte[] salt = generateSalt();
            /*
            PreparedStatement insert = conn.prepareStatement("INSERT INTO test (priKey) VALUES (?)");
            insert.setBytes(1, priKey);
            insert.executeUpdate();
            insert.close();
            System.out.println("values are succesfully inserted to database");
*/


            Statement select = conn.createStatement();
            String Sql = "SELECT * FROM signature where requestID = '"+id+"'";
            ResultSet rs  = select.executeQuery(Sql);

            try {
                while (rs.next()) {
                    receivedSalt = rs.getBytes("toBeSignedBytes");
                    // if (Arrays.equals(salt, receivedSalt))
                    //    System.out.println("match");
                    //else System.out.println("no match");
                }
            }
            finally {
                rs.close();
                select.close();
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return receivedSalt;
    }

    public static byte[] generateRandom() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }

    public static byte[][][] getKey(int number) {
        byte[][][] keyPairBytes = new byte[number][2][];
        byte[] publicKeyBytes = null,wrappedPrivateKeyBytes = null;
        int status = 0,id = 0;
        ResultSet rs = null;
        try {
            conn = getConnection();
            connStatement = conn.createStatement();


            if (dbType.equalsIgnoreCase("SQLite")){

                rs = connStatement.executeQuery( "SELECT * FROM keys where status = 0 LIMIT "+number+";" );

            }else if (dbType.equalsIgnoreCase("SQLServer")){

                String Sql = "SELECT top "+number+" * FROM keys where status = 0  and id > 1000000";
                rs  = connStatement.executeQuery(Sql);

            }

            try {
                while (rs.next()) {

                    wrappedPrivateKeyBytes = rs.getBytes("priKey");
                    publicKeyBytes = rs.getBytes("pubKey");
                    status = rs.getInt("status");
                    id = rs.getInt("id");
                    // if (Arrays.equals(salt, receivedSalt))
                    //    System.out.println("match");
                    //else System.out.println("no match");

                    if (wrappedPrivateKeyBytes != null && publicKeyBytes != null && status == 0){

                        //PreparedStatement update = conn.prepareStatement("UPDATE keys SET status = 1 , priKey=null, keyUsedTimeStamp = " +System.currentTimeMillis()+"  WHERE id = '"+id+"'");
                        //update.executeUpdate();
                        //update.close();
                        // System.out.println("values are succesfully inserted to database");

                    }

                    number = number -1;

                    keyPairBytes[number][0] = wrappedPrivateKeyBytes;
                    keyPairBytes[number][1] = publicKeyBytes;

                }

            }
            finally {
                rs.close();
                connStatement.close();
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return keyPairBytes;
    } //fixed


}