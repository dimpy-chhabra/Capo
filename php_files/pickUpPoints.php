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
	$rideID= $_POST['rid'];

	$sql = "SELECT pick_point, pick_point_time, pick_price, _pp_id FROM ride_pick_points WHERE r_id = '".$rideID."' ";
	$result = $conn->query($sql);
	if ($result->num_rows > 0) {

	while($row[] = $result->fetch_assoc()) {		 
		
		 $json = json_encode($row);
		}
		}else{
		 echo "OfferedRides: ".$result->num_rows;
		}
		echo $json;
	
	$conn->close();
?>