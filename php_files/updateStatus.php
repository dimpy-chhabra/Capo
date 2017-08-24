<?php
	$hostname = "mysql.hostinger.in";
	$username = "u714414987_user";
	$password = "pass12345";
	$dbname = "u714414987_capo";

	// Create connection
	$connection = new mysqli($hostname, $username, $password, $dbname);

	if ($connection->connect_error) {
		die("Connection failed: " . $connection->connect_error);
	} 
	
	$ride_id = $_POST["ride_id"];
	$pro_id = $_POST["pro_id"];
	$rider_id = $_POST["rider_id"];	
	$statis = $_POST["stat"];

	//$ride_id = "1";
	//$pro_id = "2";
	//$rider_id = "9999999999";	
	//$statis = "-1";
	
	
	$queryInsert = "UPDATE rides_proposals SET status = '$statis' WHERE r_id='$ride_id' AND rider_id='$rider_id' AND _pp_id='$pro_id' ";
		mysqli_query($connection,$queryInsert) or 
			die("0");	
			echo "We Capo-ed!" ;
	
	if($statis == '2'){
	$sql = "UPDATE ride_info_offered SET num_seats=num_seats-1  WHERE r_id='$ride_id'";
		mysqli_query($connection,$sql) or 
			die("0");	
			echo "We Capo-ed!" ;			
	}
	if($statis == '-1'){
	$sql = "UPDATE ride_info_offered SET num_seats=num_seats+1  WHERE r_id='$ride_id'";
		mysqli_query($connection,$sql) or 
			die("0");	
			echo "We Capo-ed!" ;			
	}

?>
