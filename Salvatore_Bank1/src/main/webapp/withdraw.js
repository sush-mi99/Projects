form4.addEventListener('submit', function (e) {
    e.preventDefault()
    checkInputs()
    if (
        document.getElementById("errorAccountNumber").innerText === "" &&
        document.getElementById("errorAmount").innerText === ""
    ) {
        console.log("no errors");
        let url = "http://localhost:8080/Salvatore_Bank1/withdraw";
        fetch(url, {
            method: 'post',
            body: JSON.stringify({
                cust_accno: document.getElementById('cust_accno').value,
                amount: document.getElementById("amount").value,
            }),
            headers: {
                'content-type': 'application/json; charset=UTF-8',
            }
        })
            .then(function (response) {
                alert("Amount withdrawn successfully");
                console.log("success");
                var results = document.getElementById('results')
                results.innerHTML = `<p>You have Successfully  Debited</p><br>
              <p><a href='/Salvatore_Bank1/withdraws'>Click Here to View Your Transaction Details</a></p><br>
    <p><a href='/Salvatore_Bank1/CustomerMenu.html'>Click Here to go to MainMenu</a></p><br>
    `})
    } else {
        console.log("errors");
        var results = document.getElementById('results')
        results.innerHTML = `<p>Deposit failed Please Try Again</p>`
    }
    function checkInputs() {
        const cust_accno = document.getElementById('cust_accno').value;
        const amount = document.getElementById('amount').value;
        let errorAccountNumber = "";
        let errorAmount = "";
       
        if (isValidAccountNumber(cust_accno)) {
            errorAccountNumber = "Account Number Cannot be Empty"
        } else {
            errorAccountNumber = "";
        }
        if (isValidAmount(amount)) {
            errorAmount = "Amount Cannot be Empty"
        }
        else {
            errorAmount = "";
        }
        document.getElementById("errorAccountNumber").innerText = errorAccountNumber;
        document.getElementById("errorAmount").innerText = errorAmount;
       
        return (errorAccountNumber.length === 0 && errorAmount.length === 0);
    }
    function isValidAccountNumber(cust_accno) {
        return cust_accno === "" || cust_accno.length < 1;
    }
    function isValidAmount(amount) {
        return amount === "" || amount.length < 2;
    }
})
