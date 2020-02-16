
   let btnSubmmit = document.getElementById("btnSb");
   let form = document.getElementById("form");
   let inputs = document.getElementsByClassName("form_input");
   let errors = document.getElementById("errors");
   let error = document.getElementById("error_message");
   let password_error = document.getElementById("password_error");
   let lengthUsernameError = document.getElementById("length_username_error");
   let lengthPasswordError  = document.getElementById("length_password_error");
   let lengthNameError = document.getElementById("name_error");

    btnSubmmit.onclick = (e) => {
            error.style.display = "none";
            password_error.style.display = "none";
            lengthUsernameError.style.display = "none";
            lengthPasswordError.style.display = "none";
            lengthNameError.style.display ="none";


            let inputsData = getValuesFromFormInputs(form);
            if(validateFormData(inputsData)){
                form.submit();
            } else {
                e.preventDefault();
            }
            
    }


    getValuesFromFormInputs = (form) => {
        let data = {};
       
        for (let i = 0; i < inputs.length; i++) {
            let name = inputs[i].getAttribute('name');
            let value = inputs[i].value;
            if (typeof name != 'undefined' && name != 'undefined')
                data[name] = value;
        }
       
        return data;
    }


    validateFormData = (data) => {
        let bool = true;
        if(!(isLatin(data.username) && isLatin(data.firstName) && isLatin(data.lastName))){
            error.style.display = 'block';
            bool = false;
        }

        if(!(data.username.length > 2 && data.username.length < 17)){
            lengthUsernameError.style.display = "block";
            bool = false;
        }

        if (!(data.password.length > 2 && data.password.length < 17)){
                lengthPasswordError.style.display = "block";
                bool = false;
           } 

        if (!(data.firstName.length > 0 && data.firstName.length < 17 &&
            data.lastName.length > 0 && data.lastName.length < 17)){
                lengthNameError.style.display = "block";
                bool = false;
            }

        if(!(validatePassword(data.password))){
            password_error.style.display = 'block';
            bool = false;
        }

        return bool;
    }

    validatePassword = (password) => {
        let reg = /(?=.*[0-9])(?=.*[A-Za-z])/;
        return reg.test(password);
    }

    isLatin = (str) => {
        let reg = /^[A-Za-z\s]+$/;
        return reg.test(str);
    }



