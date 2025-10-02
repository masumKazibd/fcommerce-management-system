<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<%@ include file="_layout.jspf" %>

<div class="max-w-5xl mx-auto p-4">
    <h1 class="text-2xl font-semibold mb-4">Dashboard</h1>
    <div id="cards" class="grid grid-cols-1 md:grid-cols-3 gap-4"></div>

    <div class="mt-8 text-sm text-gray-500">
        Period: current month (auto)
    </div>
</div>

<script>
    fetch('/api/reports/dashboard')
        .then(r => r.json())
        .then(d => {
            const box = (title, value) => `
          <div class="bg-white rounded-xl shadow p-5">
            <div class="text-gray-500 text-sm">\${title}</div>  <div class="text-2xl font-bold mt-1">\${value}</div>  </div>`;
            document.getElementById('cards').innerHTML = `
          \${box('Gross Revenue', d.grossRevenue)}  \${box('Total Expenses', d.totalExpenses)}  \${box('Net Profit', d.netProfit)}  \${box('Total COGS', d.totalCOGS)}  \${box('Total Stock Qty', d.totalStockQty)}  \${box('Stock Value (at buy price)', d.stockValue)}  `;
        })
        .catch(e => alert('Failed to load dashboard: ' + e));
</script>
</body>
</html>