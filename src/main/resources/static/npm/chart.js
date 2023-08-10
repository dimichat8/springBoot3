const ctx = document.getElementById('customerPieChart').getContext('2d');
const pieChartData = {
    labels: ['Label 1', 'Label 2', 'Label 3'], // Replace with your labels
    datasets: [{
        data: [30, 20, 50], // Replace with your data values
        backgroundColor: ['red', 'green', 'blue'], // Replace with your desired colors
    }]
};
const pieChartOptions = {
    // Add any specific options for the pie chart (e.g., legend, title, etc.)
};

new Chart(ctx, {
    type: 'pie',
    data: pieChartData,
    options: pieChartOptions,
});
