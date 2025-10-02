<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Expenses</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<%@ include file="_layout.jspf" %>

<div class="max-w-5xl mx-auto p-4">
    <h1 class="text-2xl font-semibold mb-4">Add Expense</h1>

    <form id="expForm" class="bg-white rounded-xl shadow p-5 grid grid-cols-1 md:grid-cols-4 gap-3 mb-8">
        <select name="type" class="border rounded px-3 py-2" required>
            <option value="ADVERTISING">ADVERTISING</option>
            <option value="DELIVERY">DELIVERY</option>
            <option value="OTHER">OTHER</option>
        </select>
        <input name="date" type="date" class="border rounded px-3 py-2" required />
        <input name="amount" type="number" step="0.01" placeholder="Amount" class="border rounded px-3 py-2" required />
        <input name="note" placeholder="Note (optional)" class="border rounded px-3 py-2" />
        <button class="bg-black text-white rounded px-4 py-2 md:col-span-1">Save</button>
    </form>

    <h1 class="text-2xl font-semibold mb-4 mt-8">Expense History</h1>
    <div id="expHistory" class="bg-white rounded-xl shadow">
        <table class="w-full text-left">
            <thead>
            <tr class="border-b">
                <th class="p-3">Date</th>
                <th class="p-3">Type</th>
                <th class="p-3">Amount</th>
                <th class="p-3">Note</th>
            </tr>
            </thead>
            <tbody id="expRows">
            <tr><td colspan="4" class="p-3 text-center text-gray-500">Loading expense history...</td></tr>
            </tbody>
        </table>
    </div>
</div>

<script>
    const expRows = document.getElementById('expRows');

    function loadExpenses() {
        fetch('/api/expenses')
            .then(r => r.json())
            .then(list => {
                if (list.length === 0) {
                    expRows.innerHTML = '<tr><td colspan="4" class="p-3 text-center text-gray-500">No expense history found.</td></tr>';
                    return;
                }

                expRows.innerHTML = list.map(e => {
                    const formattedDate = new Date(e.date).toLocaleDateString();
                    const formattedAmount = e.amount.toFixed(2);
                    const noteDisplay = e.note || '-';

                    return `
                        <tr class="border-b hover:bg-gray-50">
                            <td class="p-3">\${formattedDate}</td>
                            <td class="p-3">\${e.type}</td>
                            <td class="p-3">BDT \${formattedAmount}</td>
                            <td class="p-3">\${noteDisplay}</td>
                        </tr>
                    `;
                }).join('');
            })
            .catch(e => {
                expRows.innerHTML = '<tr><td colspan="4" class="p-3 text-center text-red-500">Failed to load expense history.</td></tr>';
                console.error("Failed to load expense history:", e);
            });
    }

    loadExpenses();

    document.getElementById('expForm').addEventListener('submit', async (e)=>{
        e.preventDefault();
        const fd = new FormData(e.target);
        const payload = {
            type: fd.get('type'),
            date: fd.get('date'),
            amount: Number(fd.get('amount')),
            note: fd.get('note') || null
        };
        const res = await fetch('/api/expenses', {
            method: 'POST', headers: {'Content-Type':'application/json'},
            body: JSON.stringify(payload)
        });
        if(!res.ok){ return alert('Expense failed'); }
        alert('Expense added');
        e.target.reset();

        loadExpenses();
    });
</script>
</body>
</html>