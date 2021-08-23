form2.addEventListener('submit', function (e) {
    e.preventDefault()
    checkInputs()
    if (
        document.getElementById("errorName").innerText === "" &&
        document.getElementById("errorAadharCard").innerText === "" &&
        document.getElementById("errorUserId").innerText === "" &&
        document.getElementById("errorOpeningBalance").innerText === ""
    ) {
        console.log("no errors");
        let url = "http://localhost:8080/Salvatore_Bank1/openacc";
        fetch(url, {
            method: 'post',
            body: JSON.stringify({
                cust_name: document.getElementById('cust_name').value,
                aadhar: document.getElementById("aadhar").value,
                open_bal: document.getElementById("open_bal").value,
                userId1: document.getElementById('userId1').value
            }),
            headers: {
                'content-type': 'application/json; charset=UTF-8',
            }
        })
            .then(function (response) {
                alert("Account Created successully");
                console.log("success");
                var results = document.getElementById('results')
                results.innerHTML = `<p>You have Succesfully Opened the account</p>
					 <p><a href='/Salvatore_Bank1/open'>Click Here to View Your Account Number</a></p>
                    <p><a href='/Salvatore_Bank1/CustomerMenu.html'>Click Here to menu</a></p>`})
    } else {
        console.log("errors");
        var results = document.getElementById('results')
        results.innerHTML = `<p>Account Opening failed Please Try Again</p>`
    }


    function checkInputs() {

        const cust_name = document.getElementById('cust_name').value;
        const aadhar = document.getElementById('aadhar').value;
        const userId1 = document.getElementById("userId1").value;
        const open_bal = document.getElementById("open_bal").value;

        let errorName = "";
        let errorAadhar = "";
        let errorUserId = "";
        let errorOpeningBalance = "";
        if (isValidName(cust_name)) {
            errorName = "Check your name"
        } else {
            errorName = "";
        }
        if (isValidAadharCard(aadhar)) {
            errorAadharCard = "Check your AdharNumber"
        }
        else {
            errorAadharCard = "";
        }
        if (isValidUserId(userId1)) {
            errorUserId = "Check your Id"
        } else {
            errorUserId = "";
        }
        if (isValidOpeningBalance(open_bal)) {
            errorOpeningBalance = "OpeningBalance Cannot be Zero"
        } else {
            errorOpeningBalance = "";
        }
        document.getElementById("errorName").innerText = errorName;
        document.getElementById("errorAadharCard").innerText = errorAadharCard;
        document.getElementById("errorUserId").innerText = errorUserId;
        document.getElementById("errorOpeningBalance").innerText = errorOpeningBalance;

        return (errorName === 0 && errorPanCard === 0 && errorUserId.length === 0 && errorOpeningBalance.length === 0);
    }
    function isValidName(name) {
        return name === "" || name.length < 4;
    }
    function isValidAadharCard(aadhar) {
        return aadhar === "" || aadhar.length < 12;
    }
    function isValidUserId(userId1) {
        return userId1 === "" || userId1.length < 4;
    }
    function isValidOpeningBalance(openingbalance) {
        return openingbalance === "" ;
    }
})