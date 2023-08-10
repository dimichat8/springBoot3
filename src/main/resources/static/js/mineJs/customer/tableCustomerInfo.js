var previousButtonsContainer = null;

function toggleButtons(customerInfoId) {
    var buttonsContainer = document.getElementById('buttons-' + customerInfoId);

    if (previousButtonsContainer !== null && previousButtonsContainer !== buttonsContainer) {
        previousButtonsContainer.classList.add('hidden');
    }

    buttonsContainer.classList.toggle('hidden');
    previousButtonsContainer = buttonsContainer;
}