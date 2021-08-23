form.addEventListener('submit', function (e) {
    e.preventDefault()
    checkInputs()
    if (
        document.getElementById("errorName").innerText === "" &&
        document.getElementById("errorEmailId").innerText === "" &&
        document.getElementById("errorMobileNumber").innerText === "" &&
        document.getElementById("errorCustId").innerText === "" &&
        document.getElementById("errorPassword").innerText === ""
    ) {
        console.log("no errors");
        let url = "http://localhost:8080/Salvatore_Bank1/bank";
        fetch(url, {
            method: 'post',
            body: JSON.stringify({
            	cust_name: document.getElementById('cust_name').value,
            	cust_emailid: document.getElementById('cust_emailid').value,
            	cust_phno: document.getElementById('cust_phno').value,
            	cust_id: document.getElementById('cust_id').value,
            	cust_pwd: document.getElementById('cust_pwd').value
            }),
            headers: {
                'content-type': 'application/json; charset=UTF-8',
            }
        })
            .then(function (response) {
                alert("success");
                console.log("success");
                var results = document.getElementById('results')
                results.innerHTML = `<p>You have Succesfully Registered</p>
    <p><a href='/Salvatore_Bank1'>Click Here to LOGIN</a></p>`})
    } else {
        console.log("errors");
        var results = document.getElementById('results')
        results.innerHTML = `<p>Registration failed Please Try Again</p>`
    }


    function checkInputs() {

        const cust_name = document.getElementById('cust_name').value;
        const cust_emailid = document.getElementById('cust_emailid').value;
        const cust_phno = document.getElementById('cust_phno').value;
        const cust_id = document.getElementById("cust_id").value;
        const cust_pwd = document.getElementById("cust_pwd").value;

        let errorName = "";
        let errorEmailId = "";
        let errorMobileNumber = "";
        let errorUserId = "";
        let errorPassword = "";
        if (isValidName(cust_name)) {
            errorName = "Check your name"
        } else {
            errorName = "";
        }
        if (isValidEmailId(cust_emailid)) {
            errorEmailId = "Check your mailId"
        } else {
            errorEmailId = "";
        }
        if (isValidMobileNumber(cust_phno)) {
            errorMobileNumber = "Check your mobilenumber"
        } else {
            errorMobileNumber = "";
        }
        if (isValidCustId(cust_id)) {
            errorCustId = "Check your Id"
        } else {
            errorCustId = "";
        }
        if (isValidPassword(cust_pwd)) {
            errorPassword = "Check your password"
        } else {
            errorPassword = "";
        }
        document.getElementById("errorName").innerText = errorName;
        document.getElementById("errorEmailId").innerText = errorEmailId;
        document.getElementById("errorMobileNumber").innerText = errorMobileNumber;
        document.getElementById("errorCustId").innerText = errorCustId;
        document.getElementById("errorPassword").innerText = errorPassword;

        return (errorName === 0 && errorEmailId.length === 0
            && errorMobileNumber === 0 && errorCustId.length === 0 && errorPassword.length === 0);
    }
    function isValidName(cust_name) {
        return cust_name === "" || cust_name.length < 4;
    }
    function isValidEmailId(cust_emailid) {
        return cust_emailid === "" || cust_emailid.length < 5;
    }
    function isValidMobileNumber(cust_phno) {
        return cust_phno === "" || cust_phno.length < 10;
    }
    function isValidCustId(cust_id) {
        return cust_id === "" || cust_id.length < 2;
    }
    function isValidPassword(cust_pwd) {
        return cust_pwd === "" || cust_pwd.length < 4;
    }


})


