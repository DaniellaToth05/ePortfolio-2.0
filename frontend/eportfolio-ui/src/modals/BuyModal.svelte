<script>
    let {
      open = false,
      onClose,
      onSubmit
    } = $props();
  
    let symbol = $state("");
    let name = $state("");
    let quantity = $state(0);
    let price = $state(0);
  
    function submit(e) {
      e.preventDefault();
  
      onSubmit({
        symbol,
        name,
        quantity,
        price
      });
  
      symbol = "";
      name = "";
      quantity = 0;
      price = 0;
      onClose();
    }
  </script>
  
  {#if open}
    <div class="modal-overlay">
      <div class="modal">
        <button class="close-btn" type="button" onclick={onClose}>×</button>
  
        <div class="modal-header">
          <h2 class="modal-title">Add Investment</h2>
          <p class="modal-subtitle">To Portfolio</p>
        </div>
  
        <form onsubmit={submit}>
          <div class="form-group">
            <label class="form-label" for="symbol">Symbol</label>
            <input
              id="symbol"
              class="form-input"
              bind:value={symbol}
              required
            />
          </div>
  
          <div class="form-group">
            <label class="form-label" for="name">Company Name</label>
            <input
              id="name"
              class="form-input"
              bind:value={name}
              required
            />
          </div>
  
          <div class="input-row">
            <div class="form-group">
              <label class="form-label" for="quantity">Quantity</label>
              <input
                id="quantity"
                type="number"
                class="form-input"
                bind:value={quantity}
                min="1"
                required
              />
            </div>
  
            <div class="form-group">
              <label class="form-label" for="price">Price</label>
              <input
                id="price"
                type="number"
                step="0.01"
                class="form-input price-input"
                bind:value={price}
                required
              />
            </div>
          </div>
  
          <div class="button-group">
            <button type="button" class="btn btn-secondary" onclick={onClose}>
              Cancel
            </button>
            <button type="submit" class="btn btn-primary">
              Complete Purchase
            </button>
          </div>
        </form>
      </div>
    </div>
  {/if}
  