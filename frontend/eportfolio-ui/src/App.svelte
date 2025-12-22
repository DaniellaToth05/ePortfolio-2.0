<script>
  let portfolio = null;
  let error = null;

  let symbol = "";
  let name = "";
  let quantity = 0;
  let price = 0;

  async function loadPortfolio() {
    try {
      const res = await fetch("http://localhost:8080/api/portfolio");
      if (!res.ok) throw new Error("Failed to load portfolio");
      portfolio = await res.json();
    } catch (e) {
      error = e.message;
    }
  }

  async function buyInvestment() {
    try {
      const res = await fetch("http://localhost:8080/api/buy", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          symbol,
          name,
          quantity: Number(quantity),
          price: Number(price)
        })
      });

      if (!res.ok) throw new Error("Buy failed");

      // reset form
      symbol = "";
      name = "";
      quantity = 0;
      price = 0;

      await loadPortfolio();
    } catch (e) {
      error = e.message;
    }
  }

  loadPortfolio();
</script>

<main>
  <h1>ePortfolio</h1>

  <h2>Buy Investment</h2>

  <div>
    <input placeholder="Symbol" bind:value={symbol} />
    <input placeholder="Name" bind:value={name} />
    <input type="number" placeholder="Quantity" bind:value={quantity} />
    <input type="number" placeholder="Price" bind:value={price} />
    <button on:click={buyInvestment}>Buy</button>
  </div>

  <h2>Portfolio</h2>

  {#if error}
    <p style="color: red;">{error}</p>
  {:else if !portfolio}
    <p>Loading...</p>
  {:else}
    {#if portfolio.investments.length === 0}
      <p>No investments yet.</p>
    {:else}
      <table border="1" cellpadding="8" cellspacing="0">
        <thead>
          <tr>
            <th>Symbol</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Book Value</th>
          </tr>
        </thead>
        <tbody>
          {#each portfolio.investments as inv}
            <tr>
              <td>{inv.symbol}</td>
              <td>{inv.name}</td>
              <td>{inv.quantity}</td>
              <td>${inv.price}</td>
              <td>${inv.bookValue}</td>
            </tr>
          {/each}
        </tbody>
      </table>
    {/if}
  {/if}
</main>
