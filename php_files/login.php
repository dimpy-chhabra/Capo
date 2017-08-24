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
	$password = $_POST['password'];

	$sql = "SELECT u_name, u_college, u_email, u_sex, u_enroll , u_id, u_enroll, u_fb FROM user_details WHERE u_id = '$mob_no' AND u_pass = '$password'";
	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
		// output data of each row
		while($row = $result->fetch_assoc()) {
			echo " ". $row["u_name"]. " > " . $row["u_id"]. " > " . $row["u_college"]. " > "  . $row["u_email"]. " > " . $row["u_sex"]. " > " . $row["u_enroll"]. " > " . $row["u_fb"]. "<br>";
		}
	} else {
		echo "NO_PROF" ;
	}
	$conn->close();
?>