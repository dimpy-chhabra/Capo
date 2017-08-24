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
	$driverID = $_POST['driverID'];
	$ppID = $_POST['ppID'];

	
 	$sql = "SELECT ride_pick_points. * , ride_info_offered. * , rides_proposals. * , user_details. * FROM ride_info_offered	INNER JOIN ride_pick_points ON ride_info_offered.r_id = ride_pick_points.r_id LEFT JOIN rides_proposals ON rides_proposals._pp_id = ride_pick_points._pp_id AND ride_info_offered.dri_u_id =  '$driverID' INNER JOIN user_details ON user_details.u_id = rides_proposals.rider_id WHERE ride_pick_points._pp_id = '$ppID' ";


	$result = $conn->query($sql);
	if ($result->num_rows > 0) {

	while($row[] = $result->fetch_assoc()) {		 
		 $json = json_encode($row);
		}
		}else{
		 echo "";
		}
		echo $json;
	
	$conn->close();
?>
