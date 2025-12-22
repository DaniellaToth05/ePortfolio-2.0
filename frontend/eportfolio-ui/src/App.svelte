<script>
  let portfolio = null;
  let error = null;

  async function loadPortfolio() {
    try {
      const res = await fetch("http://localhost:8080/api/portfolio");
      if (!res.ok) throw new Error("Failed to load portfolio");
      portfolio = await res.json();
    } catch (e) {
      error = e.message;
    }
  }

  loadPortfolio();
</script>

<main>
  <h1>ePortfolio</h1>

  {#if error}
    <p style="color: red;">{error}</p>
  {:else if !portfolio}
    <p>Loading portfolio...</p>
  {:else}
    <pre>{JSON.stringify(portfolio, null, 2)}</pre>
  {/if}
</main>
