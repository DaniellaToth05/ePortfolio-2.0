<script>
    let {
      investments = []
    } = $props();
  
    const hasData = $derived(() => investments.length > 0);
  
    const totalValue = $derived(() =>
      investments.reduce(
        (sum, i) => sum + i.quantity * i.price, 0
      )
    );
  </script>
  
  <div class="portfolio-card">
    <div class="portfolio-summary">
      <h2>Portfolio Overview</h2>
  
      <div class="summary-grid">
        <div class="summary-item">
          <h3>Total Portfolio Value</h3>
          <div class="value large">
            ${hasData() ? totalValue().toFixed(2) : "0.00"}
          </div>
        </div>
  
        <div class="summary-item">
          <h3>Active Holdings</h3>
          <div class="value">
            {investments.length}
          </div>
        </div>
  
        <div class="summary-item">
          <h3>Liquid Assets</h3>
          <div class="value">
            ${hasData() ? "1,500" : "0"}
          </div>
        </div>
  
        <div class="summary-item">
          <h3>Equity Ratio</h3>
          <div class="value">
            {hasData() ? "70%" : "—"}
          </div>
        </div>
      </div>
  
      {#if !hasData()}
        <div class="empty-state">
          Start tracking your investments to see performance insights here.
        </div>
      {/if}
    </div>
  
    {#if hasData()}
      <div class="top-investments">
        <div class="investments-header">Top Performers</div>
  
        <div class="investment-item">
          <span class="investment-symbol">AAPL</span>
          <span class="investment-gain">+15%</span>
        </div>
  
        <div class="investment-item">
          <span class="investment-symbol">TSLA</span>
          <span class="investment-gain">+12%</span>
        </div>
  
        <div class="investment-item">
          <span class="investment-symbol">AMZN</span>
          <span class="investment-gain">+10%</span>
        </div>
      </div>
    {/if}
  </div>
  