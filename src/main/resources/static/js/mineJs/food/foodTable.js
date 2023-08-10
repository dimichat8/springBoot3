var previousButtonsContainer = null;

function toggleButtons(foodId) {
    var buttonsContainer = document.getElementById('buttons-' + foodId);

    if (previousButtonsContainer !== null && previousButtonsContainer !== buttonsContainer) {
        previousButtonsContainer.classList.add('hidden');
    }

    buttonsContainer.classList.toggle('hidden');
    previousButtonsContainer = buttonsContainer;
}