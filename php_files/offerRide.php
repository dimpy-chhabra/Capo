<?php
	$hostname = "mysql.hostinger.in";
	$username = "u714414987_user";
	$password = "pass12345";
	$dbname = "u714414987_capo";

	// Create connection
	$conn = new mysqli($hostname, $username, $password, $dbname);

	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 
	
//print((string)$con);
$u_id = $_POST['u_id'];
$From = $_POST['from'];
$Date = $_POST['date'];
$FromTime = $_POST['fromTime'];
$To = $_POST['to'];
$ToTime = $_POST['toTime'];
$Seats = $_POST['seats'];
$Extras = $_POST['extra'];

$Pp1 = $_POST['Pp1'];
$Pp1Time =$_POST['Pp1Time'];
$Pp1Price =$_POST['Pp1Price'];

$Pp2 = $_POST['Pp2'];
$Pp2Time = $_POST['Pp2Time'];
$Pp2Price = $_POST['Pp2Price'];

$Pp3 = $_POST['Pp3'];
$Pp3Time = $_POST['Pp3Time'];
$Pp3Price = $_POST['Pp3Price'];

$r_id = "9999";

if(!isset($u_id)){echo 'id is blank.NO INPUT' ; die;}
if(!isset($Seats)){echo 'Seats is blank' ; die;}
if(!isset($From)){echo 'From is blank' ; die;}

$Sql_Query_1 = "INSERT INTO ride_info_offered(dri_u_id, r_date, from_loc, to_college, from_time, reach_time, num_seats, extras) VALUES ('$u_id' , '$Date' , '$From' , '$To', '$FromTime' , '$ToTime' , '$Seats' , '$Extras' )";
	
	mysqli_query($conn, $Sql_Query_1) or
		die(" Data Not added!") ;
		$r_id = mysqli_insert_id($conn);
		echo "  Ride Id:";
		echo $r_id;

$Sql_Query_2 = "insert into ride_pick_points(r_id, pick_point, pick_point_time, pick_price)values ( '$r_id', '$Pp1', '$Pp1Time', '$Pp1Price')";	
	mysqli_query($conn, $Sql_Query_2) or
		die(" PickUpPoint 1 Not added!") ;
		$_pp_id = mysqli_insert_id($conn);
		echo "  PP1 :";
		echo $_pp_id;

if(isset($Pp2)){
$Sql_Query_3 = "insert into ride_pick_points(r_id, pick_point, pick_point_time, pick_price)values ( '$r_id', '$Pp2', '$Pp2Time', '$Pp2Price')";	
	mysqli_query($conn, $Sql_Query_3) or
			die(" PickUpPoint 2 Not added!") ;
			$_pp_id = mysqli_insert_id($conn);
			echo "  PP2 :";			
			echo $_pp_id;
}
if(isset($Pp3)){
$Sql_Query_4 = "insert into ride_pick_points(r_id, pick_point, pick_point_time, pick_price)values ( '$r_id', '$Pp3', '$Pp3Time', '$Pp3Price')";	
	mysqli_query($conn, $Sql_Query_4) or
			die(" PickUpPoint 3 Not added!") ;
			$_pp_id = mysqli_insert_id($conn);
			echo "  PP3 :";			
			echo $_pp_id;
}		

mysqli_close($conn); 
?>
