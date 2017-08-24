<?php

$hostname = "mysql.hostinger.in";
$username = "u714414987_user";
$password = "pass12345";
$dbname = "u714414987_capo";

echo'yo bro';

$con = mysqli_connect($hostname, $username, $password, $dbname);
if (mysqli_connect_errno())
{
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
if ($con == true)
{
    echo "good connect to MySQL: " ;
}
//code begins :
//print((string)$con);
$mob_no = $_POST['mob_no'];
$name = $_POST['name'];
$email = $_POST['email'];
$college = $_POST['college'];
$dob = $_POST['dob'];
$sex = $_POST['sex'];
$pword = $_POST['pword'];
$enroll= $_POST['enroll'];
$fb_link= $_POST['fb_link'];

if(!isset($name)){echo 'Name is blank.NO INPUT' ; die;}
if(!isset($mob_no)){echo 'Phone is blank' ; die;}
if(!isset($pword)){echo 'passsword is blank' ; die;}

$Sql_Query = "insert into user_details(u_id, u_name, u_email, u_college, u_dob, u_sex, u_pass, u_enroll, u_fb )values ('$mob_no' , '$name' , '$email', '$college' , '$dob' , '$sex' , '$pword' , '$enroll' , '$fb_link' )";
//$Sql_Query_2 = "  INSERT INTO `user_login` (u_id, password) VALUES ('$mob_no'  , '$pword' ) "; 
if(mysqli_query($con, $Sql_Query)){ echo'Data Inserted Successfully';}else{echo " Data Not added!" ;}
//if(mysqli_query($con, $Sql_Query_2)){ echo'You may login ';}else{echo 'Something Went wrong! Message us!' ;}

mysqli_close($con); 

?>
