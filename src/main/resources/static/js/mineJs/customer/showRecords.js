var previousButtonsContainer = null;

function toggleButtons(customerId) {
    var buttonsContainer = document.getElementById('buttons-' + customerId);

    if (previousButtonsContainer !== null && previousButtonsContainer !== buttonsContainer) {
        previousButtonsContainer.classList.add('hidden');
    }

    buttonsContainer.classList.toggle('hidden');
    previousButtonsContainer = buttonsContainer;
}