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
	$college = $_POST['college'];
	$rider = $_POST['rider'];
	//$college="IGDTU";

//works in GET CAPO

$sql = "SELECT ride_pick_points. * , ride_info_offered. * , 
status FROM ride_info_offered
INNER JOIN ride_pick_points ON ride_info_offered.r_id = ride_pick_points.r_id
LEFT JOIN rides_proposals ON rides_proposals._pp_id = ride_pick_points._pp_id
AND rides_proposals.rider_id =  '$rider'
WHERE ride_info_offered.to_college =  '$college' ";

	
	
	$result = $conn->query($sql);
	if ($result->num_rows > 0) {
		while($row[] = $result->fetch_assoc()) {		 
		 $json = json_encode($row);
		}
	}else{
		 //echo "FoundRides: ".$result->num_rows;
	}
		echo $json;
	
	$conn->close();
?>
