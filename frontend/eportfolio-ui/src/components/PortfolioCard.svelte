<script lang="ts">
    let {
      investments = []
    } = $props<{
      investments: {
        symbol: string;
        price: number;
        quantity: number;
      }[];
    }>();
  
    const hasData = $derived(() => investments.length > 0);
  
    const totalValue = $derived(() =>
      investments.reduce(
        (sum, i) => sum + i.quantity * i.price, 0
      )
    );
  
    const largestPosition = $derived(() => {
      if (!hasData() || totalValue() === 0) {
        return null;
      }
  
      return investments.reduce(
        (max, i) => {
          const value = i.quantity * i.price;
          const percent = (value / totalValue()) * 100;
  
          return value > max.value
            ? { symbol: i.symbol, value, percent }
            : max;
        },
        { symbol: "", value: 0, percent: 0 }
      );
    });
  
    const portfolioConcentration = $derived(() => {
      if (!hasData() || totalValue() === 0) {
        return "—";
      }
      const percent = (largestPosition().value / totalValue()) * 100;
      return `${Math.round(percent)}%`;
    });
  
    const concentrationRisk = $derived(() => {
      if (!hasData() || totalValue() === 0) {
        return "—";
      }
  
      const percent =
        (largestPosition().value / totalValue()) * 100;
  
      if (percent <= 25) return "Low";
      if (percent <= 45) return "Moderate";
      return "High";
    });
  
    const topHoldings = $derived(() => {
      if (!hasData()) {
        return [];
      }
  
      return [...investments]
        .map(i => ({
          symbol: i.symbol,
          value: i.quantity * i.price
        }))
        .sort((a, b) => b.value - a.value)
        .slice(0, 3);
    });
  </script>
  
  <div class="portfolio-card">
    <div class="portfolio-summary">
      <h2>Portfolio Overview</h2>
  
      <div class="summary-grid">
        <div class="summary-item">
          <h3>
            Total Portfolio Value
            <span class="info-icon">
              ⓘ
              <span class="tooltip">
                The total market value of all your investments combined.
              </span>
            </span>
          </h3>
          <div class="value large">
            ${hasData()
              ? totalValue().toLocaleString(undefined, { minimumFractionDigits: 2 })
              : "0.00"}
          </div>
        </div>
  
        <div class="summary-item">
          <h3>
            Active Holdings
            <span class="info-icon">
              ⓘ
              <span class="tooltip">
                The number of different investments you currently own.
              </span>
            </span>
          </h3>
          <div class="value">
            {investments.length}
          </div>
        </div>
  
        <div class="summary-item">
          <h3>
            Largest Position
            <span class="info-icon">
              ⓘ
              <span class="tooltip">
                Your single biggest investment by total value and its share of your portfolio.
              </span>
            </span>
          </h3>
          <div class="value">
            {hasData()
              ? `${largestPosition().symbol} · $${largestPosition().value.toLocaleString()} (${Math.round(largestPosition().percent)}%)`
              : "—"}
          </div>
        </div>
  
        <div class="summary-item">
          <h3>
            Concentration Risk
            <span class="info-icon">
              ⓘ
              <span class="tooltip">
                How dependent your portfolio is on one holding.
                Higher concentration means higher risk if that asset drops.
              </span>
            </span>
          </h3>
          <div class="value">
            {concentrationRisk()}
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
        <div class="investments-header">Top Holdings</div>
  
        {#each topHoldings() as item}
          <div class="investment-item">
            <span class="investment-symbol">{item.symbol}</span>
            <span class="investment-gain">
              ${item.value.toLocaleString()}
            </span>
          </div>
        {/each}
      </div>
    {/if}
  </div>
  