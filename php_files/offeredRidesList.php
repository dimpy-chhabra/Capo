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
	$mob_no = $_POST['mob_no'];
	//$mob_no = " 999999999";

	//$sql = "SELECT ride_pick_points. * , ride_info_offered. * , 
//status, rider_id FROM ride_info_offered
//INNER JOIN ride_pick_points ON ride_info_offered.r_id = ride_pick_points.r_id
//INNER JOIN rides_proposals ON rides_proposals.r_id = ride_info_offered.r_id
//WHERE ride_info_offered.dri_u_id = '$mob_no' ";

$sql = "SELECT DISTINCT ride_pick_points. * , ride_info_offered. * , 
status FROM ride_info_offered
INNER JOIN ride_pick_points ON ride_info_offered.r_id = ride_pick_points.r_id
LEFT JOIN rides_proposals ON rides_proposals._pp_id = ride_pick_points._pp_id
AND ride_info_offered.dri_u_id =  '$mob_no'  ";

	//$sql = "SELECT * FROM ride_info_offered WHERE dri_u_id = '$mob_no' ";

	$result = $conn->query($sql);
	if ($result->num_rows > 0) {

	while($row[] = $result->fetch_assoc()) {		 
		
		 $json = json_encode($row);
		}
		}else{
		 //echo "OfferedRides: ".$result->num_rows;
		}
		echo $json;
	
	$conn->close();
?>
