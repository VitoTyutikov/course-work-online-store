const form = document.querySelector('#registration-form');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const clientsDTO = Object.fromEntries(formData.entries());


    const response = await fetch('/rest-user/registration', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(clientsDTO)
    })
        .then(()=>{
            window.location.replace("/login");
        })
        .catch((error)=>{
            alert(error);
        })
    const result = await response.json();
    console.log(result);

    const submitButton=document.getElementById("submit-button");
    submitButton.onclick=()=>{
        window.location.replace("/login");
    }
});
