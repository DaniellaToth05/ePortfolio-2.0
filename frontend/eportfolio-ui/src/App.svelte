<script>
  import PortfolioTable from "./lib/PortfolioTable.svelte";
  import SummaryStats from "./lib/SummaryStats.svelte";

  let portfolio = $state(null);
  let error = $state(null);

  let symbol = $state("");
  let name = $state("");
  let quantity = $state(0);
  let price = $state(0);

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

      if (!res.ok){
        throw new Error("Buy failed");
      } 

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
    <button type="button" onclick={buyInvestment}>Buy</button>
  </div>

  <h2>Portfolio</h2>

  {#if error}
    <p style="color: red;">{error}</p>
  {:else if !portfolio}
    <p>Loading...</p>
  {:else}
    <SummaryStats investments={portfolio.investments} />

    <PortfolioTable
      investments={portfolio.investments}
      loadPortfolio={loadPortfolio}
    />
  {/if}

</main>
