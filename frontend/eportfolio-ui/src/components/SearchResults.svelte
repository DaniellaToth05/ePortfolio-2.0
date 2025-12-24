<script>
    let {
      results = [],
      isFiltered = false,
      onClear
    } = $props();
  </script>
  
  <section class="search-results container">
    <div class="results-card">
      <div class="results-header">
        <h3>{isFiltered ? "Search Results" : "Portfolio"}</h3>
        {#if isFiltered}
            <button class="clear-btn" type="button" onclick={onClear}>
                Clear Search
            </button>
        {/if}
      </div>
  
      {#if results.length === 0}
        <div class="empty-state">
            {isFiltered
                ? "No investments matched your search."
                : "No investments yet — start tracking by adding your first one."}
        </div>
      {:else}
        <table class="results-table">
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
            {#each results as inv}
              <tr>
                <td class="symbol">{inv.symbol}</td>
                <td>{inv.name}</td>
                <td>{inv.quantity}</td>
                <td>${inv.price}</td>
                <td>${inv.bookValue}</td>
              </tr>
            {/each}
          </tbody>
        </table>
      {/if}
    </div>
  </section>
  
  <style>
    .search-results {
      margin-top: 4rem;
    }
  
    .results-card {
      background: var(--pearl-white);
      border-radius: 18px;
      padding: 2rem;
      box-shadow: 0 20px 50px rgba(0, 0, 0, 0.06);
    }
  
    .results-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 1.5rem;
    }
  
    .clear-btn {
      background: none;
      border: 1px solid var(--sage-light);
      color: var(--sage-dark);
      padding: 0.4rem 0.8rem;
      border-radius: 999px;
      cursor: pointer;
      font-size: 0.8rem;
    }
  
    .results-table {
      width: 100%;
      border-collapse: collapse;
    }
  
    .results-table th {
      text-align: left;
      font-size: 0.7rem;
      letter-spacing: 0.12em;
      color: var(--soft-grey);
      padding-bottom: 0.75rem;
    }
  
    .results-table td {
      padding: 1rem 0;
      border-top: 1px solid var(--stone-light);
      font-size: 0.95rem;
    }
  
    .symbol {
      font-weight: 600;
    }
  
    .empty-state {
      text-align: center;
      padding: 3rem 0;
      color: var(--soft-grey);
      font-style: italic;
    }
  </style>
  