var previousButtonsContainer = null;

function toggleButtons(customerId) {
    var buttonsContainer = document.getElementById('buttons-' + customerId);

    if (previousButtonsContainer !== null && previousButtonsContainer !== buttonsContainer) {
        previousButtonsContainer.classList.add('hidden');
    }

    buttonsContainer.classList.toggle('hidden');
    previousButtonsContainer = buttonsContainer;
}

function saveDatesAndContinue() {
    var dateFrom = document.getElementById("dateFrom").value;
    var dateTo = document.getElementById("dateTo").value;

    // Send the form data to the server using AJAX
    $.ajax({
        url: '/{customer_id}/addmeal', // Update the URL as needed
        type: 'POST',
        data: {
            dateFrom: dateFrom,
            dateTo: dateTo
        },
        success: function (data) {
            // Redirect to the mealtable page
            window.location.href = '/{customer_id}/mealtable'; // Update the URL as needed
        },
        error: function () {
            // Handle error, if any
        }
    });
    function setMealDatesAndSubmit() {
        // Get the selected dateFrom and dateTo values from the modal form
        var dateFrom = document.getElementById('dateFrom').value;
        var dateTo = document.getElementById('dateTo').value;

        // Set the values in the hidden input fields of the mealForm
        document.getElementById('dateFromInput').value = dateFrom;
        document.getElementById('dateToInput').value = dateTo;

        // Submit the mealForm
        document.getElementById('mealForm').submit();
    }

// After successful form submission, redirect to the "addmeal" page
    document.getElementById('mealForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission
        setMealDatesAndSubmit(); // Call the function to submit the form
        window.location.href = '/' + ${customer.customer_id} + '/addmeal'; // Redirect after form submission
    });
}