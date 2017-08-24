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
	//$college ="8586847364";
	$college =$_POST['user'];

//$sql = "SELECT ride_pick_points . * , ride_info_offered . * , 
//status FROM ride_info_offered
//INNER JOIN ride_pick_points ON ride_info_offered.r_id = ride_pick_points.r_id
//INNER JOIN rides_proposals ON rides_proposals.r_id = ride_info_offered.r_id
//WHERE rides_proposals.rider_id = '$college' AND rides_proposals.status = '1'";

$sql = "SELECT ride_pick_points . * , ride_info_offered . * , 
status FROM ride_info_offered
INNER JOIN rides_proposals ON rides_proposals.r_id = ride_info_offered.r_id
INNER JOIN ride_pick_points ON rides_proposals._pp_id = ride_pick_points._pp_id
WHERE rides_proposals.rider_id = '$college' AND (rides_proposals.status = '1' OR rides_proposals.status = '2' OR rides_proposals.status = '-1')";



//$sql = "SELECT ride_pick_points.pick_point,  ride_pick_points.pick_point_time, ride_pick_points.pick_price, ride_info_offered.r_date, ride_info_offered.from_loc, ride_info_offered.to_college, ride_info_offered.from_time, ride_info_offered.reach_time, ride_info_offered.num_seats, rides_proposals.status FROM ride_info_offered INNER JOIN rides_proposals ON  rides_proposals.r_id=ride_info_offered.r_id LEFT JOIN ride_pick_points ON ride_pick_points._pp_id = rides_proposals._pp_id WHERE  rides_proposals.rider_id = `$u_id`";

	
	
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
